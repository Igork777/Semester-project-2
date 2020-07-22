package handIn_1.source_code.core;

import handIn_1.source_code.view_model.SettingsViewModel;
import handIn_1.source_code.view_model.TemperaturesViewModel;

public class ViewModelFactory
{
  private TemperaturesViewModel temperaturesViewModel;
  private SettingsViewModel settingsViewModel;
  private ModelFactory modelFactory;

  public ViewModelFactory(ModelFactory mf)
  {
    this.modelFactory = mf;
  }

  public TemperaturesViewModel getTemperaturesVM() {
    if (temperaturesViewModel == null)
      temperaturesViewModel = new TemperaturesViewModel(
          modelFactory.getCloserThermometer(),
          modelFactory.getFurtherThermometer(),
          modelFactory.getOutdoorThermometer());
    return temperaturesViewModel;
  }

  public SettingsViewModel getSettingsVM() {
    if (settingsViewModel == null)
      settingsViewModel = new SettingsViewModel(modelFactory.getRadiator());
    return settingsViewModel;
  }
}
