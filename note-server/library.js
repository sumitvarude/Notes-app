var Tools = {};
var fs = require("fs");
var generateCurrentNoteNumber = function(notesCollection){
  var lastNoteNumber = 0;
  if(Object.keys(notesCollection).length > 0){
      lastNoteNumber = parseInt(Object.keys(notesCollection).slice(-1)[0]);
  }
  return  lastNoteNumber + 1;
};

Tools.saveTheNote = function(note){
  var notesCollection = Tools.getAllNotes();
  var currentNoteNumber = generateCurrentNoteNumber(notesCollection)

  notesCollection[currentNoteNumber] = note;
  fs.writeFile('./data.txt', JSON.stringify(notesCollection), function (err) {
    if (err) throw err;
    console.log('It\'s saved!');
  });
  return true;
};

Tools.getAllNotes = function(){
  return JSON.parse(fs.readFileSync('./data.txt', 'utf8'));
};

module.exports.Tools = Tools;
