export class ConfigurationShip {
  
  mastArray: Array<number>
  
  constructor(public mastCount: number) {
    this.mastArray = []
    for (let i = 1; i <= mastCount; i++) {
      this.mastArray.push(i)
    }
  }
  
  getMastCount(): number {
    return this.mastArray.length
  }
  
}
