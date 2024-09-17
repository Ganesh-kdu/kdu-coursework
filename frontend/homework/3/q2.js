function convert(day){
    return day.slice(0,3).toUpperCase();
}
function convertAll(days){
    let convertedDays = [];
    days.forEach(day => {
        convertedDays.push(convert(day));
    });
    console.log(convertedDays);
}
function code(str){
    str = str.trim()
            .replaceAll('a','4')
            .replaceAll('e','3')
            .replaceAll('i','1')
            .replaceAll('o','0')
            .replaceAll('s','5');

    console.log(str)
}
convertAll(["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]);
code("javascript is cool ");
code("programming is fun");
code("  become a coder");
