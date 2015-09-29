var http = require("http");
var dispatcher = require("./routes").dispatcher;
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

server.listen(8800, function(){
  console.log("server is listening on post 8800");
});
