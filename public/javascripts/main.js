function addResponsiveClass() {
    var x = document.getElementById("navMenu");
    if (x.className === "nav") {
        x.className += " responsive";
    } else {
        x.className = "nav";
    }
}

function pauseBackgroundAnimation() {
    document.body.classList.remove("animate-background");
    document.cookie = 'playBackground=; Max-Age=0; path=/; domain=' + location.hostname
}

function playBackgroundAnimation() {
    document.body.classList.add("animate-background");
    document.cookie = `playBackground=true; path=/`;
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

function copyToClipboard(text, originalText, clickedElement) {
    if(clickedElement.textContent != "Copied") {
        navigator.clipboard.writeText(text).then(function() {
            clickedElement.textContent = "Copied";
            clickedElement.classList.add("block-copied");
            setTimeout(function() {
                clickedElement.textContent = originalText;
                clickedElement.classList.remove("block-copied");
            }, 2000);
        }).catch(function(err) {
            console.error('Could not copy text: ', err);
        });
    }
}

document.addEventListener('click', function(event) {
    const clickedElement = event.target;
    if (clickedElement && clickedElement.classList.contains('hljs') &! clickedElement.classList.contains('block-copied')) {
        var originalCodeBlockContent = clickedElement.textContent;
        var codeBlockContent = clickedElement.textContent.trim();
        copyToClipboard(codeBlockContent, originalCodeBlockContent, clickedElement);
    }
});

if(document.cookie.includes('playBackground')) {
    document.body.classList.add("animate-background");
}