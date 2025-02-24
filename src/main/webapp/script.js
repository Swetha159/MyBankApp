document.addEventListener("DOMContentLoaded", function () {
    const sections = document.querySelectorAll(".form-section");
    const nextButtons = document.querySelectorAll(".next");
    const prevButtons = document.querySelectorAll(".prev");

    let currentSection = 0;

    function showSection(index) {
        sections.forEach((section, i) => {
            section.classList.toggle("active", i === index);
        });
    }

    nextButtons.forEach((button, index) => {
        button.addEventListener("click", function () {
            if (currentSection < sections.length - 1) {
                currentSection++;
                showSection(currentSection);
            }
        });
    });

    prevButtons.forEach((button) => {
        button.addEventListener("click", function () {
            if (currentSection > 0) {
                currentSection--;
                showSection(currentSection);
            }
        });
    });

    showSection(currentSection);
	form.addEventListener("submit", function (event) {
	        console.log("Form submitted"); // Debugging
	    });

	    showSection(currentSection);
});
