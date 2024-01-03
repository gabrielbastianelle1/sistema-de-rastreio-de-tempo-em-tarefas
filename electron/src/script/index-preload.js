const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld("myName", {
    myFunction: (callback) =>
        ipcRenderer.on("client-socket", (_event, value) => callback(value)),
});
