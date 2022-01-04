const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});
 function randomString(length, chars) {
    var result = '';
    for (var i = length; i > 0; --i) result += chars[Math.round(Math.random() * (chars.length - 1))];
    return result;
}
document.getElementById("saltKey").value='Password : '+randomString(3,'0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');

$(function () {
   $('#savedPhone').change(function () {
     if ($(this).val().length != 10 ) {
     $('.go-button').prop('disabled',true);
        }
        else{
       $('.link-verify').css('pointer-events','auto');
       $('.go-button').prop('disabled', false);
      }
    });
});

function helper_clip() {
  var copyText1 = document.getElementById("myInput");
  return copyText1;
}
function clipboard(){
var a=helper_clip().value;
var b=document.getElementById("location").value;
document.getElementById("myInput2").value=a+b;
var copyText = document.getElementById("myInput2");
  copyText.select();
  copyText.setSelectionRange(0, 99999);
  document.execCommand("copy");
  alert("Your Location is Copied to Clipboard 😊");
  document.getElementById("myInput2").style.display = "none";
}

function share_location(){
localStorage.clear();
var a=document.getElementById("savedPhone").value;
var b=document.getElementById("savedPass").value;
if(a.length<10){
alert("Please Fill Valid Phone Number before sharing!!");
}
else if(b.length<3){
alert("Please Fill Valid Password before sharing!!");
}
else{
localStorage.setItem("secure_phone",a);
localStorage.setItem("secure_pass",b);
window.location.href="share_location";
}
}
