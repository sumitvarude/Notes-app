var dispatcher = require("httpdispatcher");
var library = require("./library.js").Tools;

dispatcher.onPost("/saveNote",function(request,response){
  var noteToSave = JSON.parse(request.body).note;
  if(library.saveTheNote(noteToSave)){
    response.writeHead(200,{'Content-Type':'text/plain'});
    response.end("true");
  }
  response.writeHead(500,{'Content-Type':'text/plain'});
  response.end("false");
});

dispatcher.onGet("/getNotes", function(request,response){
  response.writeHead(200,{'Content-type':'text/plain'});
  var notesCollection = library.getAllNotes();
  response.end(JSON.stringify(notesCollection));
});

dispatcher.onPost("/deleteNote", function(request, response){
  var noteIdToDelete = request.body;
  var notes = Tools.getAllNotes();
  if(Tools.hasNote(noteIdToDelete, notes)){
    Tools.deleteNote(noteIdToDelete,notes);
    response.writeHead(200,{'Content-type':'text/plain'});
    response.end("true");
  }
  response.writeHead(500,{'Content-type':'text/plain'});
  response.end("false");
})

module.exports.dispatcher = dispatcher;
