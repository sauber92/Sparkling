const electron = require('electron')
const app = electron.app
const BrowserWindow = electron.BrowserWindow

const path = require('path')
const url = require('url')

var oauthData;
const {appNet} = require('electron')

app.on('ready', () => {
    const {net} = require('electron')
    const request = net.request('https://github.com/login/oauth/authorize?scope=user:email&client_id=7d7882ef5faa8cea3fc4')
    request.on('response', (response) => {
        console.log(`response': ${response.statusCode}`)

        response.on('data', (chunk) => {
            oauthData = chunk;
        })
        response.on('end', () => {
          console.log('No more data in response.')
        })
    })
    request.end()
})

let mainWindow;

function createWindow () {
  mainWindow = new BrowserWindow({width: 800, height: 600})

  mainWindow.loadURL(url.format({
    pathname: path.join(__dirname, 'index.html'),
    protocol: 'file:',
    slashes: true
  }))

  mainWindow.webContents.openDevTools()

  mainWindow.on('closed', function () {
    mainWindow = null
  })
}

app.on('ready', createWindow);

app.on('window-all-closed', function () {
  if (process.platform !== 'darwin') {
    app.quit()
  }
});

app.on('activate', function () {
  if (mainWindow === null) {
    createWindow()
  }
});
