//
//  SettingsViewController.h
//  Iyan3D
//
//  Created by Sankar on 06/01/16.
//  Copyright © 2016 Smackall Games. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SettingsViewController : UIViewController<UIGestureRecognizerDelegate,UIScrollViewDelegate>{
    UIScrollView *myScrollView;
}

@property (weak, nonatomic) IBOutlet UISegmentedControl *toolbarPosition;
@property (weak, nonatomic) IBOutlet UISegmentedControl *renderPreviewSize;
@property (weak, nonatomic) IBOutlet UISegmentedControl *frameCountDisplay;
@property (weak, nonatomic) IBOutlet UISegmentedControl *renderPreviewPosition;
@property (weak, nonatomic) IBOutlet UIImageView *toolbarRight;
@property (strong, nonatomic) IBOutlet UIView *masterView;
@property (weak, nonatomic) IBOutlet UIButton *doneBtn;
@property (weak, nonatomic) IBOutlet UIImageView *toolbarLeft;
@property (weak, nonatomic) IBOutlet UIImageView *renderPreviewSizeSmall;
@property (weak, nonatomic) IBOutlet UIImageView *renderPreviewSizeLarge;
@property (weak, nonatomic) IBOutlet UIImageView *framesDisplayCount;
@property (weak, nonatomic) IBOutlet UIImageView *framesDisplayDuration;
@property (weak, nonatomic) IBOutlet UIImageView *previewPositionRightBottom;
@property (weak, nonatomic) IBOutlet UIImageView *previewPositionRightTop;
@property (weak, nonatomic) IBOutlet UIImageView *previewPositionLeftBottom;
@property (weak, nonatomic) IBOutlet UIImageView *previewPositionLeftTop;
@property (weak, nonatomic) IBOutlet UIScrollView *scrolView;
- (IBAction)toolBarPositionChanged:(id)sender;
- (IBAction)renderPreviewSizeChanged:(id)sender;
- (IBAction)frameCountDisplayType:(id)sender;
- (IBAction)previewpositionChanged:(id)sender;
- (IBAction)doneBtnAction:(id)sender;
@end