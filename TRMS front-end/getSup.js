
let user = JSON.parse(sessionStorage.getItem("user"));

function getSupervisor() {

    let name = user.username;
    console.log(`Getting Supervisor with Username: ${name}`);

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {

        if(this.readyState == 4 & this.status == 200) {
             console.log(JSON.parse(this.responseText));

            if(JSON.parse(this.responseText != '{}')){
                supervisor = JSON.parse(this.responseText);
                localStorage.setItem("supUser", JSON.stringify(supervisor));
                popResult(supervisor);

                // Storage was not working, this statement fixed that by making sure
                // All the needed values where reachable before running the function.
                if(!localStorage.getItem('supUser')) {
                    populateStorage();
                  } else {
                    popResult(supervisor);
                  }
                  
            } else {
                console.log("Failed to get Supervisor... Posting")
                postCurrentSup();
            }
        }

    };

    url = `http://localhost:7000/supervisors?username=${name}`;
    xhr.open("GET", url, true);
    xhr.send();

    function popResult(supervisor) {

        // Supervisor
        let result = document.getElementById("getResult");
        result.innerHTML = '';

        let idNode = document.createElement("h3");
        let uNameNode = document.createElement("h3");
        let emailNode = document.createElement("h3");

        uNameNode.innerHTML = "Username: " + supervisor.username;
        emailNode.innerHTML = "Email: " + supervisor.email;
        idNode.innerHTML = "Id: " + supervisor.id;

        result.appendChild(idNode);
        result.appendChild(uNameNode);
        result.appendChild(emailNode);

        // Employees
        // let result2 = document.getElementById("getResult2");
        // result2.innerHTML = '';

        // let emp1Username = document.createElement("h4");
        // let emp1Email = document.createElement("h4"); 
        // let emp1Id = document.createElement("h4");
        // let emp1Reimburse = document.createElement("h4");

        // emp1Username.innerHTML = "Username: " + JSON.stringify(supervisor.employees[0].username);
        // emp1Email.innerHTML = "Email: " + JSON.stringify(supervisor.employees[0].email);
        // emp1Id.innerHTML = "Id: " + JSON.stringify(supervisor.employees[0].id);
        // emp1Reimburse.innerHTML = "Reimbursed Amount: $" + JSON.stringify(supervisor.employees[0].reimbursedAmt);

        // result2.appendChild(emp1Username);
        // result2.appendChild(emp1Email);
        // result2.appendChild(emp1Id);
        // result2.appendChild(emp1Reimburse);

        // let result3 = document.getElementById("getResult3");
        // result3.innerHTML = '';

        // let emp2Username = document.createElement("h4");
        // let emp2Email = document.createElement("h4");
        // let emp2Id = document.createElement("h4");
        // let emp2Reimburse = document.createElement("h4");

        // emp2Username.innerHTML = "Username: " + JSON.stringify(supervisor.employees[1].username);
        // emp2Email.innerHTML = "Email: " + JSON.stringify(supervisor.employees[1].email);
        // emp2Id.innerHTML = "Id: " + JSON.stringify(supervisor.employees[1].id);
        // emp2Reimburse.innerHTML = "Reimbursed Amount: $" + JSON.stringify(supervisor.employees[1].reimbursedAmt);

        // result3.appendChild(emp2Username);
        // result3.appendChild(emp2Email);
        // result3.appendChild(emp2Id);
        // result3.appendChild(emp2Reimburse);

    }

}

function postCurrentSup() {
// Because my database tables are set up the way they are, this function is needed to
// add the current user as a supervisor in the event that their 'role' in the 'employees'
// table is == 'Supervisor' and they do not exist within the supervisors table already.

    let postUName = user.username;
    let postPass = user.password;
    let postEmail = user.email;
    let postDepHead = false;
    
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 & this.status == 200) {
            console.log(this.responseText);

            if (JSON.parse(this.responseText != "{}")) {

                console.log(`${supervisor.username} added to TABLE supervisors.`)
            } else {
                console.log(`${supervisor.username} already exists in TABLE supervisors/ did not POST.`)
            }
        }
    
    }
    
    let url = "http://localhost:7000/supervisors";
    xhr.open("POST", url, true);
    
    xhr.setRequestHeader('Content-Type', 'application/json');
    
    let supervisor = {
    username: postUName,
    password: postPass,
    email: postEmail,
    depHead: postDepHead
    }
    
    xhr.send(JSON.stringify(supervisor));

    
}
