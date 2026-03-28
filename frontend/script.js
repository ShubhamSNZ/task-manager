let editingTaskId = null;
function renderTasks(tasks) {
    const grid = document.getElementById("taskGrid");
    grid.innerHTML = "";

    tasks.forEach(task => {
        grid.innerHTML += `
            <div class="card">
                <h3>${task.title}</h3>
                <p>${task.description || ""}</p>

                <span class="status ${task.status.toLowerCase()}">
                    ${task.status}
                </span>

                <p>Priority: ${task.priority}</p>

                <p></p>Due: ${new Date(task.dueDate).toLocaleDateString()}</p>

                <button onclick="markComplete(${task.id})">✔</button>
                <button onclick="deleteTask(${task.id})">🗑</button>
                <button onclick="editTask(${task.id})">✏️</button>
            </div>
        `;
    });
}

async function loadTasks() {
    const res = await fetch("http://localhost:8080/api/tasks");
    const data = await res.json();
    renderTasks(data);
}

function openModal() {
    document.getElementById("modal").classList.remove("hidden");
}

function closeModal() {
    document.getElementById("modal").classList.add("hidden");
}

async function addTask() {
    const task = {
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        priority: document.getElementById("priority").value,
        dueDate: document.getElementById("dueDate").value,
        category: document.getElementById("category").value
    };

    if (editingTaskId) {
        // UPDATE
        await fetch(`http://localhost:8080/api/tasks/${editingTaskId}`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(task)
            
        });
        showToast("Task Updated ✏️");
        editingTaskId = null;
    } else {
        // CREATE
        await fetch(`http://localhost:8080/api/tasks`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(task)
        });
        showToast("Task Added ✅");
    }

    closeModal();
    loadTasks();
}
async function markComplete(id) {
    await fetch(`http://localhost:8080/api/tasks/${id}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ status: "COMPLETED" })
    });
    showToast("Task Completed 🎉");
    loadTasks();
}

async function deleteTask(id) {
    await fetch(`http://localhost:8080/api/tasks/${id}`, {
        method: "DELETE"
    });
    showToast("Task Deleted 🗑");
    loadTasks();
}

async function editTask(id) {
    const res = await fetch(`http://localhost:8080/api/tasks/${id}`);
    const task = await res.json();

    editingTaskId = id;

    document.getElementById("title").value = task.title;
    document.getElementById("description").value = task.description;
    document.getElementById("priority").value = task.priority;
    document.getElementById("dueDate").value = task.dueDate;
    document.getElementById("category").value = task.category;

    openModal();
}

async function filterStatus(status) {
    const res = await fetch(`http://localhost:8080/api/tasks/status?status=${status}`);
    const data = await res.json();
    renderTasks(data);
}

async function filterCategory() {
    const category = document.getElementById("categoryFilter").value;

    const res = await fetch(`http://localhost:8080/api/tasks/category?category=${category}`);
    const data = await res.json();

    renderTasks(data);
}

function showToast(message) {
    const toast = document.getElementById("toast");
    toast.innerText = message;
    toast.classList.remove("hidden");

    setTimeout(() => {
        toast.classList.add("hidden");
    }, 2000);
}

loadTasks();