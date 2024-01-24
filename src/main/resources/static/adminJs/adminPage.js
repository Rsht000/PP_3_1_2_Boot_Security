const  url = '/api/admin/getUsers'

async function getAdminPage() {
    let page = await fetch(url)
    if (page.ok) {
        let ListUsers = await page.json();
        getAllUsers(ListUsers);
    } else {
        alert(`Error, ${page.status}`);
    }
}
function getAllUsers(ListUsers) {
    const tableBody = document.getElementById('tbody');
    let dataHtml = '';

    for (let user of ListUsers) {
        let roles = [];

        for (let role of user.roles) {
            roles.push(" " + role.role.toString())
        }
        dataHtml += `<tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${roles}</td>
            <td>
                <button class="btn btn-primary" data-bs-toggle="modal" 
                           data-bs-target="#editModal"
                           onclick="editModalData(${user.id})">
                           Edit
                </button>
            </td>
            <td>
                <button class="btn btn-danger" data-bs-toggle="modal" 
                           data-bs-target="#deleteModal"
                           onclick="deleteModalData(${user.id})">
                    Delete
                </button>
            </td>
            </tr>`
    }
    tableBody.innerHTML = dataHtml;
}

getAdminPage();