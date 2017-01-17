const electron = require('electron')
// Module to control application life.
const app = electron.app
// Module to create native browser window.
const BrowserWindow = electron.BrowserWindow

const path = require('path')
const url = require('url')

// 첫 시작시 로그아웃 상태로 만들기!
function init() {
  location.href("https://api.github.com/authorizations/1");
}
init();

// 깃헙 확인
var oauthData;

    const {appNet} = require('electron')
    app.on('ready', () => {
      const {net} = require('electron')
      const request = net.request('https://github.com/login/oauth/authorize?scope=user:email&client_id=7d7882ef5faa8cea3fc4')
      request.on('response', (response) => {
        console.log(`response': ${response.statusCode}`)
      //console.log(`HEADERS: ${JSON.stringify(response.headers)}`)

      response.on('data', (chunk) => {
        oauthData = chunk;



        //console.log(`BODY: ${chunk}`)
        })
        response.on('end', () => {
          console.log('No more data in response.')
        })
      })
      request.end()
    })
// electron net end



// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
let mainWindow

function createWindow () {
  // Create the browser window.
  mainWindow = new BrowserWindow({width: 800, height: 600})

  // and load the index.html of the app.
  mainWindow.loadURL(url.format({
    pathname: path.join(__dirname, 'index.html'),
    protocol: 'file:',
    slashes: true
  }))

  // Open the DevTools.
  mainWindow.webContents.openDevTools()

  // Emitted when the window is closed.
  mainWindow.on('closed', function () {
    // Dereference the window object, usually you would store windows
    // in an array if your app supports multi windows, this is the time
    // when you should delete the corresponding element.
    mainWindow = null
  })
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on('ready', createWindow)

// Quit when all windows are closed.
app.on('window-all-closed', function () {
  // On OS X it is common for applications and their menu bar
  // to stay active until the user quits explicitly with Cmd + Q
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', function () {
  // On OS X it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  if (mainWindow === null) {
    createWindow()
  }
})

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and require them here.
