# quiz


Hi team , Here , I have developed two kind for user interface for that problem statement.
1> with client interaction like chat app with provide the questions and verify them by user.
2> Rest Api, where You can get question from server and after that pass the question and sum of that question to server for verification.


Here I have provided some of urls to testing the scenarion . Here its ambiguity.

for first approch ,  You cam use http://localhost:5000/ for entering the window ,after that user shoud ask like (provide me a question) as 
proper input and then server will provide the questions to use after that user should send message with question and sum(sum should provide like 
answer is 17) .then server will check the scenario.


For second approch , where you can give requestbody like 
{
    "name": "jenil",
    "content": "Please provise give me addition questions "
}

then sevver will provide random question to you after that for sum user should provide question body with sum in URL 
so we can identify whether its correct request according to previous request then send responde based on sum.

http://localhost:5000/question (post method)
http://localhost:5000/sum/{sum} (post method)



