import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ColorService {
  typeColors: Map<string, [string, string]> = new Map<string, [string, string]>();

  public generateVibrantColors(index: number): [string, string] {
    const baseHue = (index * 137) % 360; // Using the golden angle approximation for distribution
    const color1 = `hsl(${baseHue}, 70%, 55%)`;
    const color2 = `hsl(${(baseHue + 45) % 360}, 70%, 55%)`; // Offset by 45 degrees for the second color
    return [color1, color2];
  }

  public mapTypesToColors(typeLists: string[]) {
    const typeColors = new Map<string, [string, string]>();
    typeLists.forEach((type, index) => {
      typeColors.set(type, this.generateVibrantColors(index));
    });
    this.typeColors = typeColors;
  }

  public getTypeGradient(type: string): [string, string] {
    const colors = this.typeColors.get(type);
    if (colors) {
      return colors;
    } else {
      // Default color pair if the type is not found
      return ['rgba(0, 0, 0, 0.7)', 'rgba(255, 255, 255, 0.7)'];
    }
  }
}
