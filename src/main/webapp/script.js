function nextSection(current) {
    let section = document.getElementById(`section${current}`);
    let inputs = section.querySelectorAll("input, select");
    
    for (let input of inputs) {
        if (!input.checkValidity()) {
            input.reportValidity();  // Show native error message
            return;
        }
    }
	

    document.getElementById(`section${current}`).style.display = "none";
    document.getElementById(`section${current + 1}`).style.display = "block";
}

function prevSection(current) {
    document.getElementById(`section${current}`).style.display = "none";
    document.getElementById(`section${current - 1}`).style.display = "block";
}