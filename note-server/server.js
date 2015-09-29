var http = require("http");
var fs = require("fs");
var dispatcher = require("httpdispatcher");
var port = 8800;
var handleRequest = function (request, response){
  try {
        //log the request on console
        console.log(request.url);
        //Disptach
        dispatcher.dispatch(request, response);
    } catch(err) {
        console.log(err);
    }
};
var server = http.createServer(handleRequest);

var generateCurrentNoteNumber = function(notesCollection){
  var lastNoteNumber = 0;
  if(Object.keys(notesCollection).length > 0){
      lastNoteNumber = parseInt(Object.keys(notesCollection).slice(-1)[0]);
  }
  return  lastNoteNumber + 1;
};

var saveTheNote = function(note){
  var notesCollection = getAllNotes();
  var currentNoteNumber = generateCurrentNoteNumber(notesCollection)

  notesCollection[currentNoteNumber] = note;
  fs.writeFile('./data.txt', JSON.stringify(notesCollection), function (err) {
    if (err) throw err;
    console.log('It\'s saved!');
  });
  return true;
};

var getAllNotes = function(){
  return JSON.parse(fs.readFileSync('./data.txt', 'utf8'));
};

dispatcher.onPost("/saveNote",function(request,response){
  var notToSave = JSON.parse(request.body).note;
  if(saveTheNote(notToSave)){
    response.writeHead(200,{'Content-Type':'text/plain'});
    response.end("true");
  }
  response.writeHead(500,{'Content-Type':'text/plain'});
  response.end("false");
});

dispatcher.onGet("/getNotes", function(request,response){

  response.writeHead(200,{'Content-type':'text/plain'});
  response.end("ghe tuzya notes");
})

server.listen(8800, function(){
  console.log("server is listening on post 8800");
});
