function printKeysAndValues(obj) {
    const keys = [];
    const values = [];
  
    function processObject(obj) {
      for (const key in obj) {
        keys.push(key);
        if (typeof obj[key] === 'object' && obj[key] !== null) {
          processObject(obj[key]); 
        } else {
          values.push(obj[key]);
        }
      }
    }
  
    processObject(obj);
  
    console.log("All the keys:", keys);
    console.log("All the values:", values);
  }
  const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
      country: "Spain",
      city: "Barcelona",
    },
    careerInfo: {
      fcBarcelona: {
        appearances: 780,
        goals: {
          premierLeagueGoals: 590,
          championsLeagueGoals: 50,
        },
      },
    },
  };
  
  printKeysAndValues(player);
  