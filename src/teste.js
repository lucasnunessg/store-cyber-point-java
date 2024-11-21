

const players = [

  { name: 'Michael Jordan', height: 198, team: 'Chicago Bulls' },

  { name: 'LeBron James', height: 203, team: 'Los Angeles Lakers' },

  { name: 'Kobe Bryant', height: 198, team: 'Los Angeles Lakers' },

  { name: 'Stephen Curry', height: 191, team: 'Golden State Warriors' },

  { name: 'Kevin Durant', height: 211, team: 'Brooklyn Nets' },

];

console.log(players);


console.log("Nomes jogadores de Basquete")

const addTimeFavorito = (players) => players.forEach((timeFavorito) => timeFavorito.favorito = "Grêmio");


addTimeFavorito(players)
console.log(players);




