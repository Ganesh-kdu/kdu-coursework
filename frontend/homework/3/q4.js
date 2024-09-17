let  inputString = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';
inputString = inputString.slice(1,inputString.length-1);
const keyValuePairs = inputString.split(',');

const modifiedObject = {};

keyValuePairs.forEach(pair => {
  const [key, value] = pair.split(':');
  const trimmedKey = key.trim().replaceAll("\"", ''); 
  let modifiedValue = value.trim().replaceAll("\"", '');
  if (trimmedKey !== 'email') {
    modifiedValue = modifiedValue.toUpperCase();
  }
  if(!isNaN(parseInt(modifiedValue)))
    modifiedValue = parseInt(modifiedValue);

  modifiedObject[trimmedKey] = modifiedValue;
});

console.log(modifiedObject);
