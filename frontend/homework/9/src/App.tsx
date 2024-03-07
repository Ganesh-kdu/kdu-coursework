import { Details } from "./components/details";

function App() {
  const response = {
    name: "Ganesh",
    fullName: "Ganesh Setty",
    designation: "SDE",
    skills: [
      {
        id: 1,
        skill: "Python",
      },
      {
        id: 2,
        skill: "React",
      },
      {
        id: 3,
        skill: "Express",
      },
    ],
    hobbies: [
      {
        id: 1,
        hobby: "CSGO",
      },
    ],
  };
  return (<Details data = {response}/>);
}

export default App;
