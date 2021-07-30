
function postRForm() {

    user = JSON.parse(sessionStorage.getItem("user"));
    console.log("Adding Reimbursement Form...")
    
    let postLocation = document.getElementById("location").value;
    let postDescription = document.getElementById("description").value;
    let postCost = document.getElementById("cost").value;
    let postGradeFormat = document.getElementById("gradeFormat").value;
    let postEventType = document.getElementById("eventType").value;

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 & this.status == 200) {
            console.log(this.responseText);

            if (JSON.parse(this.responseText != "{}")) {

                document.getElementById("postResult").innerHTML = "Reimbursement Form added!"
            } else {
                document.getElementById("postResult").innerHTML = "Reimbursement Form was not added!"
            }
        } else if (this.readyState == 4) {
            document.getElementById("postResult").innerHTML = "Request failed."
        }
    
    }
    
    let url = "http://localhost:7000/rforms";
    xhr.open("POST", url, true);
    
    xhr.setRequestHeader('Content-Type', 'application/json');
    
    let reimburseForm = {
    locale: postLocation,
    description: postDescription,
    cost: postCost,
    gradeFormat: postGradeFormat,
    eventType: postEventType,
    employee: user,
    }
    
    xhr.send(JSON.stringify(reimburseForm));
    

}