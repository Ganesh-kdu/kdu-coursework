import { useState } from 'react';
import Header from './components/header';
import MainContainer from './components/container';

function App() {
  const [searchQuery, setSearchQuery] = useState("");
  return (
    <>
      <Header setQuery = {setSearchQuery}/>
      <MainContainer query = {searchQuery}/>
    </>
  );
}

export default App;
