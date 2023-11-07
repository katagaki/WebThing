function deleteItem(itemID) {
    fetch('/items/' + itemID, {
        method: 'DELETE',
        credentials: 'include'
    })
    .then(response => {
        response.json()
        .then(data => {
            if(data) {
                location.reload();
            }
        });
    });
}

function createItem() {
    const form = document.getElementById('newItem');
    const formData = {};
    const formElements = form.elements;
    for(let i = 0; i < formElements.length; i++) {
        const field = formElements[i];
        formData[field.name] = field.value;
    }
    fetch('/items/new', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        response.json()
        .then(data => {
            if(data) {
                location.href = "/myitems";
            }
        });
    });
}