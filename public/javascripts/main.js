function addResponsiveClass() {
    var x = document.getElementById("navMenu");
    if (x.className === "nav") {
        x.className += " responsive";
    } else {
        x.className = "nav";
    }
}

function randomMessage() {
    var x = document.getElementById("index-random-sequence");
    var messages = [
        "Let's code something fun today!",
        "Will code for cash!",
        "Consumes coffee and outputs code!",
        "Consumed by code!",
        "Refactoring my code one byte at a time!",
        "Like my frontend? Thanks! My backend's alright too!",
        "Scala, Python, C#, JavaScript, Terraform, Bash... and so on!",
        "I really like making games, you know?",
        "I really like making websites, you know?",
        "418? Why yes, I'll take a coffee!"
    ]
    var randomIndex = Math.floor(Math.random() * messages.length);
    x.innerHTML = messages[randomIndex];
}
