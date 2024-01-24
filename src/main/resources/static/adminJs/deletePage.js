const form_del = document.getElementById('formForDeleting');
const id_del = document.getElementById('id_del');
const username_del = document.getElementById('username_del');
const surname_del = document.getElementById('surname_del');
const age_del = document.getElementById('age_del');
const email_del = document.getElementById('email_del');

async function deleteModalData(id) {
    const urlForDel = '/api/admin/getUser/' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                username_del.value = `${user.username}`;
                surname_del.value = `${user.surname}`;
                age_del.value = `${user.age}`;
                email_del.value = `${user.email}`;
            })
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}

function deleteUser() {

    form_del.addEventListener('submit', deletingUser);

    function deletingUser(event) {
        event.preventDefault();
        let url = '/api/admin/getUser/' + id_del.value

        let method = {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json"
            }
        }

        fetch(url, method).then(() => {
            $("#deleteCloseBtn").click();
            getAdminPage();
        });
    }
}

