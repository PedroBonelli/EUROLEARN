function hideElement(elementId) {
    document.getElementById(elementId).classList.add('display-none');
}

function showElement(elementId) {
    document.getElementById(elementId).classList.remove('display-none');
}




function main(){

    const filterPicker = document.getElementById('filter-picker');

    hideElement('filter-cpf');
    hideElement('filter-email');
    hideElement('filter-nome');

    filterPicker.addEventListener('change', () => {

        const selectedOption = filterPicker.value;

        console.log(selectedOption);

        if(selectedOption == 1){
            hideElement('filter-cpf');
            hideElement('filter-email');
            showElement('filter-nome');
        }else if(selectedOption == 2){
            hideElement('filter-nome');
            hideElement('filter-email');
            showElement('filter-cpf');
        } else if(selectedOption == 3){
            hideElement('filter-nome');
            hideElement('filter-cpf');
            showElement('filter-email');
        }
    });


}

main();