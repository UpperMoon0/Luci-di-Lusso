import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ColorService {
  public generateVibrantColors(index: number): [string, string] {
    const baseHue = (index * 137) % 360; // Using the golden angle approximation for distribution
    const color1 = `hsl(${baseHue}, 70%, 55%)`;
    const color2 = `hsl(${(baseHue + 45) % 360}, 70%, 55%)`; // Offset by 45 degrees for the second color
    return [color1, color2];
  }

  public mapTypesToColors(typeLists: string[]): Map<string, [string, string]> {
    const typeColors = new Map<string, [string, string]>();
    typeLists.forEach((type, index) => {
      typeColors.set(type, this.generateVibrantColors(index));
    });
    return typeColors;
  }

}
