const path = require('path');

const express = require ('express');
const http = require('http');

const publicPath = path.join(__dirname, '../public');
var app = express();
var server = http.createServer(app);


app.use(express.static(publicPath));

app.get('/hallo', function (request,response) {
   response.send("Es Hat funktioniert");
});


server.listen(3008, function() {
    console.log('Started on port 3008');
});

