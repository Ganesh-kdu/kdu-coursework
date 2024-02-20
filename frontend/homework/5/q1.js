import os from "os";
import fs from "fs";
import http from "http";
import url from "url";
function getSystemInfo() {
    return {
        HostName: os.hostname(),
        OperatingSystem: os.type(),
        Architecture: os.arch(),
        OSRelease: os.version(),
        Uptime: os.uptime(),
        NumberOfCPUCores: os.cpus().length,
        TotalMemory: os.totalmem(),
        FreeMemory: os.freemem(),
        CurrentWorkingDirectory: os.homedir(),
    };
}

function writeToFile(data, filename = "systeminfo.json") {
    fs.writeFileSync(filename, JSON.stringify(data));
}
const systemInfo = getSystemInfo();
writeToFile(systemInfo);
const server = http.createServer((req, res) => {
    console.log( url.parse(req.url, true));
    if (req.method === "GET" && req.url === "/") {
        fs.readFile("systeminfo.json",(err,data)=>{
            res.writeHead(200, { "Content-Type": "application/json" });
            res.end(data);
        });
        
    } else {
        res.writeHead(404, { "Content-Type": "text/plain" });
        res.end("Not Found");
    }
});

server.listen(5000, () => {
    console.log("Server is running on port 5000");
});