import { useEffect, useState } from 'react';
import api from './FetchApi';

interface Person {
    id: number;
    fullname: string;
		username: string;
		email: string;
		address: string;
		role: string;
}

function Persons() {
  const [persons, setPersons] = useState<Person[]>([]);

  useEffect(() => {
    async function fetchPersons() {
      try {
        const response = await api.get("/persons")
        setPersons(response.data);
      }catch(e) {
        console.error(e)
      }
    }
    fetchPersons()
  }, [])

  return(
    <div>
      <h1> Pessoas cadastradas </h1>
      <ul>
        {persons.map((person) => (
          <div key={person.id}>
            <h2>{person.fullname}</h2>
          </div>
        ))}
      </ul>
    </div>
  )
}

export default Persons;