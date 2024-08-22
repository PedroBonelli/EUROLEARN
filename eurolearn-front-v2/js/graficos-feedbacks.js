async function getFeedbackData(filterType){
    let url = '';

    switch (filterType) {
        case 'month':
            url = "http://localhost:8080/restfeedbacks/groupbylast12months";
            break;
    
        default:
            break;
    } 

    const getFeedback = new Request(url);
    const response = await fetch(getFeedback);
    return await response.json();
}

function cleanData(data){
    const cleanDates = [];
    for(let i = 0; i<data[0].length; i++){
        cleanDates.push(data[0][i].slice(0, 7).replace("-", "/"));
    }
    data[0] = cleanDates;
}

export default function buildBarGraph(data, htmlCanvasId){  

    cleanData(data);

    let canvas = document.getElementById(htmlCanvasId);

    new Chart(canvas, {
        type: 'line',
        data: {
            labels: data[0],
            datasets: [{
                label: 'Índice de satisfação médio',
                data: data[1],
                borderWidth: 2
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

