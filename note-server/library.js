var Tools = {};
var fs = require("fs");
var generateCurrentNoteNumber = function(notesCollection){
  var lastNoteNumber = 0;
  if(Object.keys(notesCollection).length > 0){
      lastNoteNumber = parseInt(Object.keys(notesCollection).slice(-1)[0]);
  }
  return  lastNoteNumber + 1;
};

var saveNotesCollectionToFS = function(notesCollection){
  fs.writeFile('./data.txt', JSON.stringify(notesCollection), function (err) {
    if (err) throw err;
    console.log('It\'s saved!');
  });
};

Tools.saveTheNote = function(note){
  var notesCollection = Tools.getAllNotes();
  var currentNoteNumber = generateCurrentNoteNumber(notesCollection)

  notesCollection[currentNoteNumber] = note;
  saveNotesCollectionToFS(notesCollection);
  return true;
};

Tools.getAllNotes = function(){
  return JSON.parse(fs.readFileSync('./data.txt', 'utf8'));
};

Tools.hasNote = function(noteNumber, notes){
  if(notes.hasOwnProperty(noteNumber))
    return true;
  return false;
};

Tools.rearrangeNotesWithout = function(noteNumber, notes){
  var size = Object.keys(notes).length;
  for (var i = parseInt(noteNumber); i <= size; i++) {
    var noteTochange = i.toString();
    var noteTosetNumber = i+1;
    var noteToSet = noteTosetNumber.toString();
    notes[noteTochange] = notes[noteToSet];
  }
  delete notes[Object.keys(notes).length];
  return notes;
};

Tools.deleteNote = function(noteNumber,notes){
  var allNotes = notes
  delete allNotes[noteNumber];
  var rearrangedNotes = Tools.rearrangeNotesWithout(noteNumber, allNotes);
  saveNotesCollectionToFS(rearrangedNotes);
};

module.exports.Tools = Tools;
