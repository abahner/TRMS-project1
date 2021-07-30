
function getEmployee() {


    console.log("Getting Employee");

    let id = user.id;
    console.log(`Getting Employee with the ID of: ${id}`);

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {

        if(this.readyState == 4 & this.status == 200) {
            console.log(this.responseText);
            if(JSON.parse(this.responseText != '{}')){
                let employee = JSON.parse(this.responseText);
                popResult(employee);
            } else {
                document.getElementById("getResult").innerHTML = "Invalid or Undefined ID.";
            }
        }
    };

    url = `http://localhost:7000/employees/${id}`;
    xhr.open("GET", url, true);
    xhr.send();

    function popResult(employee) {

        let result = document.getElementById("getResult");
        result.innerHTML = '';

        let idNode = document.createElement("h3");
        let uNameNode = document.createElement("h3");
        let passNode = document.createElement("h3");
        let emailNode = document.createElement("h3");
        let roleNode = document.createElement("h3");
        let reimbursedAmtNode = document.createElement("h3");
        let coursesNode = document.createElement("h3");

        idNode.innerHTML = "ID: " + employee.id;
        uNameNode.innerHTML = "Username: " + employee.username;
        passNode.innerHTML = "Password: " + employee.password;
        emailNode.innerHTML = "Email: " + employee.email;
        roleNode.innerHTML = "Role: " + employee.role;
        reimbursedAmtNode.innerHTML = "Reimbursed Amount: " + employee.reimbursedAmt;

        let courses = JSON.stringify(employee.courses);
        coursesNode.innerHTML = "Courses: <br>" + courses;

        result.appendChild(idNode);
        result.appendChild(uNameNode);
        result.appendChild(passNode);
        result.appendChild(emailNode);
        result.appendChild(roleNode);
        result.appendChild(reimbursedAmtNode);
        result.appendChild(coursesNode);


    }

}