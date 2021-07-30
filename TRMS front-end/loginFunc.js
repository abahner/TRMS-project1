let uname = document.getElementById("inputUname");
let pass = document.getElementById("inputPass");

function login(){

    console.log(`Checking user: ${uname.value} for input password: ${pass.value}`);

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {

        if (this.readyState == 4 & this.status == 200) {
            console.log(this.responseText);

            // Checks is not empty
            if (JSON.parse(this.responseText != '{}')) {

                // Storing the employee in session storage for later use
                let employee = JSON.parse(this.responseText);
                sessionStorage.setItem("user", JSON.stringify(employee));

                // Validates credenditals
                if (uname.value == employee.username & pass.value == employee.password) {
                    console.log("Success! logging in...")

                    // Checks employees title and sends that user to the corresponding page.

                    switch (employee.role) {

                        case "Benefits Coordinator":
                            window.location.href = "bencoIndex.html";
                            break;

                        case "Supervisor":
                            window.location.href = "supIndex.html";
                            break;

                        default:
                            window.location.href = "empIndex.html";
                    }

                } else if (uname.value != employee.uname || pass.value != employee.pass){
                    document.getElementById("loginResult").innerHTML = "Incorrect credentials";
                }
            } else {
                document.getElementById("loginResult").innerHTML = `Username: ${uname.value} does not exist.`;
            }
        }
    };

    url = `http://localhost:7000/employees?username=${uname.value}`;
    xhr.open("GET", url, true);
    xhr.send();

}




