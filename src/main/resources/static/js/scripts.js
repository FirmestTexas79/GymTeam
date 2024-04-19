// Funkce pro kontrolní popup okno před smazáním záznamu
function confirmDelete() {
    var deleteButtons = document.querySelectorAll('.delete-btn');
    deleteButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            var confirmDelete = confirm("Opravdu chcete smazat tento záznam?");
            if (confirmDelete) {
                var url = button.getAttribute('data-url');
                window.location.href = url;
            }
        });
    });
}

// Zavolání funkce po načtení DOM
document.addEventListener('DOMContentLoaded', function () {
    confirmDelete();
});
