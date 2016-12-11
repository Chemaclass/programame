#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main ()
{
    int hours=0, minutes=0, minutesLeft=0;
    char str[] = "00:01";
    while (1) {
        scanf("%s", str);
        if (strcmp(str, "00:00") == 0) {
            break;
        }
        hours = atoi(strtok (str,":"));
        minutes = atoi(strtok (NULL, ":"));
        minutesLeft = (23-hours)*60 + (60-minutes);
        printf("%d\n", minutesLeft);
    }

    return 0;
}
