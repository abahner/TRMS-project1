
let user = JSON.parse(sessionStorage.getItem("user"));

function getBenco() {

    let name = user.username;

    console.log(`Getting Benefits Coordinator with Username: ${name}`);

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {

        if (this.readyState == 4 && this.status == 200) {
            console.log(JSON.parse(this.responseText));

            if (JSON.parse(this.responseText != '{}')){
                benCo = JSON.parse(this.responseText);
                popResult(benCo)
                
            } else {
                console.log("Failed to get Benefit Coordinator... Posting")
                postCurrentBenco();
            }

        }

    }

    url = `http://localhost:7000/bencoords?username=${name}`;
    xhr.open("GET", url, true);
    xhr.send();

    function popResult(benCo) {

        let idNode = document.getElementById("bencoId")
        let uNameNode = document.getElementById("bencoUname");
        let emailNode = document.getElementById("bencoEmail");
        let supervisorNode = document.getElementById("bencoSup");
        let supervisorNode2 = document.getElementById("bencoSupEmail");


        idNode.innerHTML = benCo.id;
        uNameNode.innerHTML = name
        emailNode.innerHTML = benCo.email;

        let supervisorName = benCo.supervisor.username;
        let supervisorEmail = benCo.supervisor.email;

        supervisorNode.innerHTML =  supervisorName
        supervisorNode2.innerHTML = supervisorEmail

        

    }

}


function postCurrentBenco() {

    let postUName = user.username;
    let postPass = user.password;
    let postEmail = user.email;
    
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 & this.status == 200) {
            console.log(this.responseText);

            if (JSON.parse(this.responseText != "{}")) {

                console.log(`${benefitsCoordinator.username} added to TABLE benfits_coordinators`)
            } else {
                console.log(`${benefitsCoordinator.username} already exists in TABLE benfits_coordinators/ did not POST.`)
            }
        }
    
    }
    
    let url = "http://localhost:7000/bencoords";
    xhr.open("POST", url, true);
    
    xhr.setRequestHeader('Content-Type', 'application/json');
    
    let benefitsCoordinator = {
    username: postUName,
    password: postPass,
    email: postEmail
    }
    
    xhr.send(JSON.stringify(benefitsCoordinator));

}