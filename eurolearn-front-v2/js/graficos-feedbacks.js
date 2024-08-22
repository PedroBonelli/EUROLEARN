async function getFeedbackData(filterType){
    let url = '';

    switch (filterType) {
        case 'month':
            url = "http://localhost:8080/restfeedbacks/groupbymonths";
            break;
    
        default:
            break;
    } 

    const getFeedback = new Request(url);
    const response = await fetch(getFeedback);
    return await response.json();
}

function cleanData(data){
   Object.keys(data).forEach((key) => {

        if (data[key] == NaN || data[key] == null) {
            data[key] = 0;
   }
})

}

export default function buildBarGraph(data, htmlCanvasId){

    cleanData(data);

    let canvas = document.getElementById(htmlCanvasId);

    new Chart(canvas, {
        type: 'bar',
        data: {
            labels: Object.keys(data),
            datasets: [{
                label: '# of Votes',
                data: Object.values(data),
                borderWidth: 1
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

