// preload.js

// All the Node.js APIs are available in the preload process.
// It has the same sandbox as a Chrome extension.

const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld("ipc", {
    signup: (data) => {
        ipcRenderer.send("signup", data);
    },
});

// contextBridge.exposeInMainWorld("server", {
//     connect: (callback) => {
//         ipcRenderer.on("client-socket", (_event, value) => callback(value));
//     },
// });

window.addEventListener("DOMContentLoaded", () => {
    const replaceText = (selector, text) => {
        const element = document.getElementById(selector);
        if (element) element.innerText = text;
    };

    for (const dependency of ["chrome", "node", "electron"]) {
        replaceText(`${dependency}-version`, process.versions[dependency]);
    }
});
