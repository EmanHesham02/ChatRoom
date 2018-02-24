function register() {
    var fName = $("#fName").val();
    var lName = $("#lName").val();
    var userName = $("#userName").val();
    var pass = $("#pass").val();
    var email = $("#email").val();
    var objMessage = {"fName": fName, "lName": lName, "userName": userName, "pass": pass, "email": email};
    $.ajax({
        url: 'RegServlet',
        type: 'GET',
        contentType: 'application/json',
        data: objMessage,
        dataType: 'json',
        success: function (value) {
            console.log("Success");
        }
    });
}
function login() {

    var userName = document.getElementById("loginuserName").value;
    var pass = document.getElementById("loginpass").value;
    var objMessage = {"name": userName, "pass": pass};
//    $.ajax({
//        url: 'RegServlet',
//        type: 'POST',
//        contentType: 'application/json',
//        data: objMessage,
//        dataType: 'json',
//        success: function (value) {
//            
    $.post("RegServlet", objMessage, function (data)
    {
        console.log("Success");
        var value = data;
        if (value === "faild") {
            window.location = "Register.html";
            console.log("Success");
        } else {
        window.location = "index.html";
        }

    });
//           

}



