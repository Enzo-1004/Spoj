// Solução do João

int x2, y2;
double distancia = 0;


Console.WriteLine("Digite a coordenada X do tesouro: ");
x2 = int.Parse(Console.ReadLine());
Console.WriteLine("Digite a coordenada Y do tesouro: ");
y2 = int.Parse(Console.ReadLine());

while (x2 == 0 && y2 == 0 )
{
    Console.WriteLine("Valores inválidos. Digite novamente.");
    Console.WriteLine("Digite a coordenada X do tesouro: ");
    x2 = int.Parse(Console.ReadLine());
    Console.WriteLine("Digite a coordenada Y do tesouro: ");
    y2 = int.Parse(Console.ReadLine());
}

int x1 = 0, y1 = 0;

while (true)
{
    if (x1 != x2 && y1 != y2)
    {
        distancia += Math.Round(Math.Sqrt(2), 4);
        int a = x1;
        int b = x2;
        x1 += (x1 < x2) ? 1 : -1;
        y1 += (y1 < y2) ? 1 : -1;
        string direc = (a<x1 && b<y1) ? "NE" : (a<x1 && b>y1) ? "SE" : (a>x1 && b<y1) ? "NO" : "SO";
        Console.WriteLine(x1 + " " + y1 + " " + direc);
    }
    else if (x1 != x2 && y1 == y2)
    {
        int a = x1;
        distancia += 1;
        x1 += (x1 < x2) ? 1 : -1;
        string direc = (a < x1) ? "O" : (a < x1) ? "L" : "SO";
        Console.WriteLine(x1 + " " + y1 + " " + direc);
    }
    else if (y1 != y2 && x1 == x2)
    {
        int b = y1;
        distancia += 1;
        y1 += (y1 < y2) ? 1 : -1;
        string direc = (b < y1) ? "N" : (b > y1) ? "S" : "";
        Console.WriteLine(x1 + " " + y1 + " " + direc);
    }
    if (x1 == x2 && y1 == y2)
    {
        break;
    }
}

Console.WriteLine("A distância percorrida foi de: " + distancia);