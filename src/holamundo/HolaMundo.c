#include <stdio.h>

int main ()
{
    int i=0,amount=0;
    char str[] = "Hola mundo.";
    scanf("%d", &amount);
    for (i=0;i<amount;i++) {
        printf("%s\n", str);
    }
    return 0;
}
