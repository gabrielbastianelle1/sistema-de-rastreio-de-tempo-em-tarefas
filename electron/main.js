// main.js

// Modules to control application life and create native browser window
const { app, BrowserWindow, ipcMain, clipboard } = require("electron");
const path = require("node:path");
const net = require("node:net");

const client = net.createConnection({
    port: 4000,
    host: "172.18.0.4",
});

const createWindow = () => {
    // Create the browser window.
    const mainWindow = new BrowserWindow({
        width: 1100,
        height: 768,
        minWidth: 1000,
        minHeight: 700,
        resizable: false,
        webPreferences: {
            preload: path.join(__dirname, "preload.js"),
        },
    });

    //mainWindow.loadURL("http://localhost:3000");
    mainWindow.setMenu(null);
    mainWindow.loadURL(`file://${__dirname}/src/index.html`);

    mainWindow.webContents.openDevTools();
};

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Algumas APIs podem ser usadas somente depois que este evento ocorre.
app.whenReady().then(() => {
    createWindow();

    app.on("activate", () => {
        if (BrowserWindow.getAllWindows().length === 0) createWindow();
    });
});

app.on("window-all-closed", () => {
    if (process.platform !== "darwin") app.quit();
});

/**
 * Requests to java backend
 */
ipcMain.on("signup", (_event, data) => {
    console.log("cheguei no ipcMain");
    //client.write(data + "\n");

    client.write("/user/signup" + "\r\n");
});
