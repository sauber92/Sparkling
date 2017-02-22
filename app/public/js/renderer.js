// Sparkling Functions
function brewCommand(command) {
    console.log('start brewCommand');

    const spawn = require('child_process').spawn;
    const brew = spawn('brew', [command]);
    var logger = document.getElementById("print-log");

    brew.stdout.on('data', (data) => {
        console.log(`stdout: ${data}`);
        alert("Complete");
        logger.innerHTML += `${data}`;
    });

    brew.stderr.on('data', (data) => {
        console.log(`stderr: ${data}`);
        alert.write("ERROR");
    });

    brew.on('close', (code) => {
        console.log(`child process exited with code ${code}`);
    });
}

function brewInput(command, formula) {
    console.log('start brewInput');
    console.log(formula);

    const spawn = require('child_process').spawn;
    const brew = spawn('brew', [command, formula]);
    var logger = document.getElementById("print-log");

    brew.stdout.on('data', (data) => {
        console.log(`stdout: ${data}`);
        alert("Complete");
        logger.innerHTML += `${data}`;
    });

    brew.stderr.on('data', (data) => {
        console.log(`stderr: ${data}`);
        alert("Err");
        logger.innerHTML += `${data}`;
    });

    brew.on('close', (code) => {
        console.log(`child process exited with code ${code}`);
    });
}