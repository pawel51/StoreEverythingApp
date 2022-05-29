const name = document.querySelector('#name');
const surname = document.querySelector('#surname');
const age = document.querySelector('#age');
const username = document.querySelector('#username');
const password = document.querySelector('#password');

const btnRegister = document.querySelector('.btn-register');
const alertP = document.querySelector('.register-alert');



const register = () => {
    if(name.value === '' || surname.value === '' || age.value === '' || username.value === '' || password.value === ''){
        alertP.innerText = 'Uzupełnij wszystkie pola'
    }else
    {
        // alert('Poprawnie uzupełnione wszystkie pola');
        alertP.innerText = '';
    }
};

btnRegister.addEventListener('click', register);