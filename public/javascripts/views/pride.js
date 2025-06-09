function displayCode(elementId) {
    var hiddenElementId = elementId + "-code";
    var hiddenHtmlElement = elementId + "-html";
    var replaceSubstring = "onclick=\"displayCode(&quot;" + elementId + "&quot;)\"";
    console.log(replaceSubstring);
    
    var hiddenElement = document.getElementById(hiddenElementId);
    var element = document.getElementById(elementId);

    hiddenElement.hidden = !hiddenElement.hidden;

    var elementHtml = element.outerHTML;
    console.log(elementHtml);
    var updatedElement = elementHtml.replace(replaceSubstring,'');

    document.getElementById(hiddenHtmlElement).innerText = updatedElement;
}
