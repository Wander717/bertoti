1.	What precisely do we mean by software engineering? What distinguishes “software engineering” from “programming” or “computer science”? And why would Google have a unique perspective to add to the corpus of previous software engineering literature written over the past 50 years? The terms “programming” and “software engineering” have been used interchangeably for quite some time in our industry, although each term has a different emphasis and different implications. University students tend to study computer science and get jobs writing code as “programmers.” “Software engineering,” however, sounds more serious, as if it implies the application of some theoretical knowledge to build something real and precise. Mechanical engineers, civil engineers, aeronautical engineers, and those in other engineering disciplines all practice engineering. They all work in the real world and use the application of their theoretical knowledge to create something real. Software engineers also create “something real,” though it is less tangible than the things other engineers create. Unlike those more established engineering professions, current software engineering theory or practice is not nearly as rigorous. Aeronautical engineers must follow rigid guidelines and practices, because errors in their calculations can cause real damage; programming, on the whole, has traditionally not followed such rigorous practices. But, as software becomes more integrated into our lives, we must adopt and rely on more rigorous engineering methods. We hope this book helps others see a path toward more reliable software practices.


2.	Programming Over Time We propose that “software engineering” encompasses not just the act of writing code, but all of the tools and processes an organization uses to build and maintain that code over time. What practices can a software organization introduce that will best keep its code valuable over the long term? How can engineers make a codebase more sustainable and the software engineering discipline itself more rigorous? We don’t have fundamental answers to these questions, but we hope that Google’s collective experience over the past two decades illuminates possible paths toward finding those answers. One key insight we share in this book is that software engineering can be thought of as “programming integrated over time.” What practices can we introduce to our code to make it sustainable—able to react to necessary change—over its life cycle, from conception to introduction to maintenance to deprecation? The book emphasizes three fundamental principles that we feel software organizations should keep in mind when designing, architecting, and writing their code: 
 
Time and Change: How code will need to adapt over the length of its life 
 
Scale and Growth: How an organization will need to adapt as it evolves 
 
Trade-offs and Costs: How an organization makes decisions, based on the lessons of Time and Change and Scale and Growth


                                        Respostas
20/02/2026

1.	Comentar com suas palavras o primeiro trecho do livro Software Engineering at Google, Oreilly. (primeiro post)

De fato, os termos “engenharia de software” e “programação” vem sendo utilizados de forma bastante genérica nos últimos anos, sendo vistos como sinônimos para muitas pessoas, principalmente as mais leigas ou iniciantes da área.  
O objetivo principal da engenharia é identificar um problema que atrapalha ou impede a vida das pessoas em uma determinada circunstância, pensar em métodos para resolver ou mitigar o problema e, por fim, desenvolver uma solução. A programação, neste caso, seria vista como um método a ser utilizado para resolver algum problema ou adversidade. 

2. Comentar com suas palavras o segundo trecho do livro Software Engineering at Google, Oreilly.(segundo post)

Os desafios propostos pelo parágrafo são recorrentes não só na área da engenharia de software, como também em várias outras áreas. Algumas das soluções que as empresas e funcionários encontram e colocam em prática são: Planejamento sólido, documentação, metodologias mais eficientes, etc.

3. Listar e explicar 3 exemplos de tradeoffs

a) Prestação de serviço: Quando uma pessoa não tem capacitação, tempo ou afinidade para executar um determinado serviço ou reparo, a mesma contrata outra pessoa para o fazer.
   
b) Tempo x Dinheiro: A pessoa trabalha mais horas para ter uma compensação maior em dinheiro (horas extras). 

c) Produção x Qualidade: Um desafio inerente à praticamente todas as fábricas. Para obter o máximo de produção, a qualidade ficará comprometida, e se priorizar a qualidade, a produção será menor.

20/02/2026

3. Requisitos não funcionais 

Requisitos não funcionais servem para descrever como um determinado sistema ou programa irá funcionar, ignorando o que ele irá fazer. 

Listando 5 requisitos não funcionais: 
a) Segurança: Um sistema deve possuir a segurança como uma das prioridades principais, principalmente se for um sistema que atuará com dados sensíveis ou até mesmo em áreas financeiras de uma determinada empresa, por exemplo. 
b) Usabilidade: É interessante que o sistema seja de fácil uso e entendimento para a maioria dos usuários, mesmo para os que possuem menos afinidades com sistemas digitais.
c) Confiabilidade: É de suma importante que o sistema seja, acima de tudo, confiável e estável em quase todas as circunstâncias. 
d) Agilidade: O tempo de resposta de um programa ou sistema é um fator importante para muitas funcionalidades ou potenciais usuários.
e) Robustez: Por fim, é interessante que o sistema tenha uma boa facilidade para resolver os erros em potencial.


23/02/2026

5. É possível testar tudo? 
    int blench (int j) {
        j = j-1; //deveria ser j+1
        j = j/30000
        return j;
    }
    -32768 até +32767

a) Quantas entradas mostram o erro? 
b) Quais são? 

                                        Respostas

a) 4 entradas
b) +30000, -30000, 29999, -29999


