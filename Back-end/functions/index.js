const functions = require('firebase-functions');

const json = [
    {
        "title": "Terminator 2",
        "year": 1992,
        "rating": 5.0,
        "tags": ["Action", "Sci-Fi"],
        "description": "A cyborg, identical to the one who failed to kill Sarah Connor, " +
            "must now protect her teenage son, John Connor, from a more advanced and powerful cyborg.",
        "poster": "https://m.media-amazon.com/images/M/MV5BMGU2NzRmZjUtOGUxYS00ZjdjLWEwZWItY2NlM2JhNjkxNTFmXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg"
    },
    {
        "title": "Titanic",
        "year": 1997,
        "rating": 4.5,
        "tags": ["Drama", "Romance"],
        "description": "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the " +
            "luxurious, ill-fated R.M.S. Titanic.",
        "poster": "https://m.media-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_UX182_CR0,0,182,268_AL_.jpg"
    },
    {
        "title": "Avatar",
        "year": 2009,
        "rating": 4.0,
        "tags": ["Action", "Adventure", "Fantasy"],
        "description": "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between " +
            "following his orders and protecting the world he feels is his home.",
        "poster": "https://m.media-amazon.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_UX182_CR0,0,182,268_AL_.jpg"
    },
    {
        "title": "True lies",
        "year": 1994,
        "rating": 3.5,
        "tags": ["Action", "Comedy", "Thriller"],
        "description": "A fearless, globe-trotting, terrorist-battling secret agent has his life turned upside down " +
            "when he discovers his wife might be having an affair with a used car salesman while terrorists smuggle " +
            "nuclear war heads into the United States.",
        "poster": "https://m.media-amazon.com/images/M/MV5BYzg5YmUyNGMtMThiNS00MjA2LTgwZDctNDlhM2RkZDNmZmRkXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_UX182_CR0,0,182,268_AL_.jpg"
    }
];
exports.movies = functions.https.onRequest((request, response) => {response.send(json);
});
