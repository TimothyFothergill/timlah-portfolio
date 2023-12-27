function addResponsiveClass() {
    var x = document.getElementById("navMenu");
    if (x.className === "nav") {
        x.className += " responsive";
    } else {
        x.className = "nav";
    }
}
