// const net = require("net");
// const readline = require("readline");

// const client = new net.Socket();
// client.connect(4000, "172.18.0.4", () => {
//     console.log("funcionou sa merda");
// });

const net = require("node:net");
const client = net.createConnection({ port: 4000, host: "app" }, () => {
    console.log("connected to server!");
    client.write("hello krai \r\n");
    client.write("hello krai 2 \r\n");
    client.write("hello krai 3 \r\n");
});

// client.on("data", (data) => {
//     console.log(data.toString());
//     client.end();
// });
// client.on("end", () => {
//     console.log("disconnected from server");
// });
