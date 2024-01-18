const form_del = document.getElementById('formForDeleting');
const id_del = document.getElementById('id_del');
const username_del = document.getElementById('username_del');
const email_del = document.getElementById('email_del');
const password_del = document.getElementById('password_del');

async function deleteModalData(id) {
    const urlForDel = '/api/admin/getUser/' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                username_del.value = `${user.username}`;
                email_del.value = `${user.email}`;
                password_del.value = `${user.password}`;
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

