var a = 0;
var b = 0;
var c = 0;
var equation = 0;
var score = 0;

function generateEquationEasy() {
  var varNumbers = [];
  for (var i = 0; i < 3; i++) {
   varNumbers.push(Math.floor(Math.random() * 10 + 1));
  }
  a = varNumbers[0];
  b = varNumbers[1];
  c = varNumbers[2];
  let equation = a + " + (" + b + " * " + c + ")";
  document.getElementById("algebraGame").style.display = "block";
  document.getElementById("question").innerHTML = equation;
  answersEasy(a,b,c)
}

function answersEasy(a,b,c) {
  var dict = {};
  var answers = [];
  var randomAnswer = [];

  answers.push( (a + (b * c)) );
  answers.push( ((a + b) + c) );
  answers.push( ((a * b) + c) );
  answers.push( (a * (b + c)) );

  while ( Object.keys(dict).length < 4){
    var num = Math.floor(Math.random() * 4);

    if (!(num in dict)) {
      dict[num] = 1;
      randomAnswer.push(answers[num]);
    }
  }

  document.getElementById("aLabel").innerHTML = randomAnswer[0];
  document.getElementById("bLabel").innerHTML = randomAnswer[1];
  document.getElementById("cLabel").innerHTML = randomAnswer[2];
  document.getElementById("dLabel").innerHTML = randomAnswer[3];

  document.getElementById("A").value = randomAnswer[0];
  document.getElementById("B").value = randomAnswer[1];
  document.getElementById("C").value = randomAnswer[2];
  document.getElementById("D").value = randomAnswer[3];

  equation = a + (b * c);
}
function isCorrect() {
  var radio = document.getElementsByName('answer'); 

  for(i = 0; i < radio.length; i++) { 
      if(radio[i].checked) {
        if(radio[i].value == equation){
          score++;
          document.getElementById("message").innerHTML = "Nice!";
        }
        else {
          score--;
          document.getElementById("message").innerHTML = "Try Again...";
        }
        generateEquationEasy()
        break;
      }
  }
  document.getElementById("score").innerHTML = score;

}
function timer() {

  score = 0;

  document.getElementById("startGame").style.display = "none";

  // Set the date we're counting down to
  var countDownDate = new Date().getTime() + 10000;

  // Update the count down every 1 second
  var x = setInterval(function() {

    // Get today's date and time
    var now = new Date().getTime();
      
    // Find the distance between now and the count down date
    var distance = countDownDate - now;
      
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
      
    // Output the result in an element with id="demo"
    document.getElementById("timer").innerHTML = minutes + "m " + seconds + "s ";
      
    // If the count down is over, write some text 
    if (distance < 0) {
      clearInterval(x);
      document.getElementById("timer").innerHTML = "TIMES UP";
      document.getElementById("startGame").style.display = "block";
      document.getElementById("algebraGame").style.display = "none";

      var jsonData = 
      { 
        "name" : "Donald Duck",
        "highScore" : score,
        "attempts" : "1" 
      }

      saveData = $.ajax({
            type: 'POST',
            url: "/api/v1/players/",
            data: JSON.stringify(jsonData),
            dataType: "json",
            contentType: "application/json",
            success: function(resultData) { console.log("Save Complete") }
      });

    }
  }, 100);
}