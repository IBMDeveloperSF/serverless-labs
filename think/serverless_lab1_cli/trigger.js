function main(params) {
  var date = new Date();
  var time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
  console.log("The time is " + time);
  return { message: "The time is " + time };
}